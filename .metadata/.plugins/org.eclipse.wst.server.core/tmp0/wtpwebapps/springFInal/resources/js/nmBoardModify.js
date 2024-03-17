console.log("nmBoardModify.js in");


{/* <div class="ms-2 me-auto">
<div class="fw-bold">${nmfvo.nmFileName}</div>
<span class="badge rounded-pill text-bg-secondary">${nmfvo.nmFileSize}Byte</span>
<button type="button" data-uuid="${nmfvo.nmUuid }" class="btn btn-sm btn-outline-danger file-x">x</button>
</div> */}

document.addEventListener('click', (e) => {
    console.log(e.target);
    if (e.target.classList.contains('file-x')) {
        let uuid = e.target.dataset.uuid;
        let li = e.target.closest('li');
        console.log(uuid);
        removeFileFromServer(uuid).then(result => {
            if (result == '1') {
                alert('파일이 삭제되었습니다.');
                li.remove();
            }
        })
    }
})

async function removeFileFromServer(uuid) {
    try {
        const url = "/board/" + uuid;
        const config = {
            method: "delete"
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
