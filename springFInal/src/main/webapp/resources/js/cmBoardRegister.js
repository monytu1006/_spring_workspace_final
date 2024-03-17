console.log("cmBoardRegister js in");


document.getElementById("trigger").addEventListener("click", () => {
    document.getElementById("files").click();
});


const regExp = new RegExp(".(exe|sh|bat|dll}jar|msi)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(cmFileName, cmFileSize) {
    if (regExp.test(cmFileName)) {
        return 0;
    } else if (cmFileSize > maxSize) {
        return 0;
    } else {
        return 1;
    }
}



document.addEventListener("change", (e) => {
    console.log(e.target);
    if (e.target.id === "files") {

        const fileobject = document.getElementById("files").files;
        console.log(fileobject);


        document.getElementById("regBtn").disabled = false;

        let div = document.getElementById("fileZone");
        div.innerHTML = "";

        let isOk = 1;
        let ul = `<ul class="list-group list gruop-flush">`;
        for (let file of fileobject) {

            let ValidResult = fileValidation(file.name, file.size);
            isOk *= ValidResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3"> `;
            ul += `${
                ValidResult
                    ? '<div class="fw-bold"> 업로드 가능</div>'
                    : '<div class="fw-bold text-danger"> 업로드 불가능</div>'
            }`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill text-bg-${
                ValidResult ? "success" : "danger"
            }">${file.size}Byte</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if (isOk == 0) {

            document.getElementById("regBtn").disabled = true;
        }
    }
});