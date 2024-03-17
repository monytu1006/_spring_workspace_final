console.log("cmBoardComment js in");
console.log(">>>> cmBnoVal >>>>>" + cmBnoVal);

document.getElementById("cmtPostBtn").addEventListener("click", () => {
    const cmtText = document.getElementById("cmtText");
    if (cmtText.value == null || cmtText.value == "") {
        alert("댓글을 입력해주세요.");
        cmtText.focus();
        return false;
    } else {
        let cmtData = {
            cmBno: cmBnoVal,
            cmWriter: document.getElementById("cmtWriter").innerText,
            cmContent: cmtText.value,
        };
        console.log(cmtData);

        postCommentToServer(cmtData).then(result => {
            if (result == '1') {
                alert('댓글이 등록되었습니다.');
                cmtText.value = '';
            }
            spreadCommentList(cmtData.cmBno);
        });
    }
});


async function postCommentToServer(cmtData) {
    try {
        const url = "/cmcomment/post";
        const config = {
            method: "post",
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(cmBno, page) {
    try {
        const resp = await fetch("/cmcomment/" + cmBno + "/"+ page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


function spreadCommentList(cmBno, page=1){
    getCommentListFromServer(cmBno, page).then(result =>{
        console.log(result);

        if(result.cmtList.length > 0){
            const ul =document.getElementById("cmtListArea");
            if(page ==1){
                ul.innerHTML = "";
            }
            for(let cmcvo of result.cmtList){
                let li = `<li class="list-group-item" data-cmcno="${cmcvo.cmCno}" data-cmwriter="${cmcvo.cmWriter}">`
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">${cmcvo.cmWriter}</div>`;
                li += `${cmcvo.cmContent}`;
                li += `</div>`;
                li += `<span class="badge rounded-pill text-bg-warning">${cmcvo.cmModAt}</span>`;
                if (authNick === cmcvo.cmWriter) {
                    li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
                    li += `<button type="button" class="btn btn-outline-danger del">x</button>`;
                } else {
                    li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal" style="display: none;">e</button>`;
                    li += `<button type="button" class="btn btn-outline-danger del" style="display: none;">x</button>`;
                }
                li +=`</li>`
                ul.innerHTML +=li;
            }
             
            let moreBtn = document.getElementById("moreBtn");
            console.log(moreBtn);

            if (result.pgvo.pageNo < result.endPage) {
                moreBtn.style.visibility = "visible";
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = "hidden";
            }
        } else {
            const ul = document.getElementById("cmtListArea")
            let li = `<li class="list-group-item">Comment List Empty</li>`;
            ul.innerHTML += li;
        }
    })
};

document.addEventListener('click', (e)=>{
    console.log(e.target);

    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);

        spreadCommentList(cmBnoVal, page);

    }else if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bold').nextSibling; 
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;

        document.getElementById('cmtModBtn').setAttribute("data-cmcno", li.dataset.cmcno);
        document.getElementById('cmtModBtn').setAttribute("data-cmwriter", li.dataset.cmwriter);
    }else if(e.target.id == 'cmtModBtn'){
        let cmtDataMod={
            cno: e.target.dataset.cmcno,
            writer: e.target.dataset.cmwriter,
            content: document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);

        editCommentToServer(cmtDataMod).then(result=>{
            if(result == "1"){ 
                alert("댓글 수정 완료");
                document.querySelector('.btn-close').click();

            }else{
                alert("댓글 수정 실패");
                document.querySelector('.btn-close').click();
            }
            spreadCommentList(cmBnoVal);
        })
    } else if(e.target.classList.contains("del")){
        let li = e.target.closest('li');
        console.log(li)
        let cmCno = li.dataset.cmcno;
        let cmWriter = li.dataset.cmwriter;
        console.log(cmCno)
        console.log(cmWriter)

        deleteCommentToServer(cmCno,cmWriter).then((result)=>{
            if(result == "1"){
                alert("댓글 삭제 완료")
                spreadCommentList(cmBnoVal);
            }
        })
    }


});

async function editCommentToServer(cmtDataMod) {
    try {
        const url = "/cmcomment/edit";
        const config = {
            method: "put",
            headers: {
                "content-type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(cmtDataMod),
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteCommentToServer(cmCno,cmWriter){
    try {
        const url ='/comment/delete/'+ cmCno + "/" + cmWriter;
        const config ={
            method : 'delete',
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}




