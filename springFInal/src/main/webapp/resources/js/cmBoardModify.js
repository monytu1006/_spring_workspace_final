console.log("cmBoardModify.js in");

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
