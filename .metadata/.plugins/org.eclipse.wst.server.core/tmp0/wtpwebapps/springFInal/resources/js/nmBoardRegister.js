console.log("nmBoardRegister js in");


document.getElementById("trigger").addEventListener("click", () => {
    document.getElementById("files").click();
});


const regExp = new RegExp(".(exe|sh|bat|dll}jar|msi)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(nmFileName, nmFileSize) {
    if (regExp.test(nmFileName)) {
        return 0;
    } else if (nmFileSize > maxSize) {
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

		// <!-- file 입력 라인 추가 -->
		// <div class="mb-3">
		// 	<input type="file" class="form-control" name="files" id="files" multiple="multiple" style="display: none"> <br>
		// 	<!-- 파일 버튼 프리거 사용하기 위해서 주는 버튼 -->
		// 	<button type="button" class="btn btn-outline-primary" id="trigger">FileUpload</button>		
		// </div>		
		
		// <!-- 첨부파일 쵸기될 영역 -->
		// <!-- 파일 목록 표시라인 -->
		// <div class="mb-3" id="fileZone">
		
		// </div>
		// <button type="submit" class="btn btn-dark" id="regBtn">등록</button>

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