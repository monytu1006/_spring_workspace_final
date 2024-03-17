console.log("nmBoardComment js in");
console.log(">>>> nmBnoVal >>>>>" + nmBnoVal);

document.getElementById("cmtPostBtn").addEventListener("click", () => {
    const cmtText = document.getElementById("cmtText");
    if (cmtText.value == null || cmtText.value == "") {
        alert("댓글을 입력해주세요.");
        cmtText.focus();
        return false;
    } else {
        let cmtData = {
            nmBno: nmBnoVal,
            nmWriter: document.getElementById("cmtWriter").innerText,
            nmContent: cmtText.value,
        };
        console.log(cmtData);

        postCommentToServer(cmtData).then(result => {
            if (result == '1') {
                alert('댓글이 등록되었습니다.');
                cmtText.value = '';
            }
            spreadCommentList(cmtData.nmBno);
        });
    }
});


async function postCommentToServer(cmtData) {
    try {
        const url = "/comment/post";
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


async function getCommentListFromServer(nmBno, page) {
    try {
        const resp = await fetch("/comment/" + nmBno + "/"+ page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}


function spreadCommentList(nmBno, page=1){
    getCommentListFromServer(nmBno, page).then(result =>{
        console.log(result);

        if(result.cmtList.length > 0){
            const ul =document.getElementById("cmtListArea");
            if(page ==1){
                ul.innerHTML = "";
            }
            for(let nmcvo of result.cmtList){
                let li = `<li class="list-group-item" data-nmcno="${nmcvo.nmCno}" data-nmwriter="${nmcvo.nmWriter}">`
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">${nmcvo.nmWriter}</div>`;
                li += `${nmcvo.nmContent}`;
                li += `</div>`;
                li += `<span class="badge rounded-pill text-bg-warning">${nmcvo.nmModAt}</span>`;
                // li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
                // li += `<button type="button" class="btn btn-outline-danger del">x</button>`;

                // 추가로 댓글 글쓴이와 차후 로그인한 사람이 동일인물이 아닐경우 
                // li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">e</button>`;
                // li += `<button type="button" class="btn btn-outline-danger del">x</button>`;
                // 부분이 보이지 않도록
                // 동일인일 경우만 보이도록 해서 동작하도록 설정 
                if (authNick === nmcvo.nmWriter) {
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
            let li = `<li class="list-group-item">등록된 댓글이 없습니다.</li>`;
            ul.innerHTML += li;
        }
    })
};

document.addEventListener('click', (e)=>{
    console.log(e.target);

    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);

        spreadCommentList(nmBnoVal, page);

    }else if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        let cmtText = li.querySelector('.fw-bold').nextSibling; 
        console.log(cmtText);
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;

        document.getElementById('cmtModBtn').setAttribute("data-nmcno", li.dataset.nmcno);
        document.getElementById('cmtModBtn').setAttribute("data-nmwriter", li.dataset.nmwriter);
        
    }else if(e.target.id == 'cmtModBtn'){
        let cmtDataMod={
            nmCno: e.target.dataset.nmcno,
            nmWriter: e.target.dataset.nmwriter,
            nmContent: document.getElementById('cmtTextMod').value
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
            spreadCommentList(nmBnoVal);
        })
    } else if(e.target.classList.contains("del")){
        let li = e.target.closest('li');
        console.log(li)
        let nmCno = li.dataset.nmcno;
        let nmWriter = li.dataset.nmwriter;
        console.log(nmCno)
        console.log(nmWriter)

        deleteCommentToServer(nmCno,nmWriter).then((result)=>{
            if(result == "1"){
                alert("댓글 삭제 완료")
                spreadCommentList(nmBnoVal);
            }
        })
    }


});

async function editCommentToServer(cmtDataMod) {
    console.log(cmtDataMod)
    try {
        const url = "/comment/edit";
        const config = {
            method: 'put',
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}


async function deleteCommentToServer(nmCno,nmWriter){
    try {
        const url ='/comment/delete/'+ nmCno + "/" + nmWriter;
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




