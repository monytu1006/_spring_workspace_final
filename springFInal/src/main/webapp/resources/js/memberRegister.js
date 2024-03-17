console.log('memberRegister 입장');

let confirmId = false;

document.getElementById('validationId').addEventListener('click', () => {
    let email = document.getElementById('email');
    console.log(email.value);
    if (email.value == null || email.value == "") {
        alert('아이디를 입력해주세요.');
        email.focus();
        return false;
    } else {
        validationIdConfirm(email.value).then(result => {
            console.log(result);
            if (result == "1") {
                alert('사용 가능한 이메일입니다.');
                confirmId = true;
            } else if (result == "0") {
                alert('이미 존재하는 이메일입니다.');
                email.value = '';
            }
        })
    }
})

async function validationIdConfirm(email) {
    try {
        const url = "/member/" + email;
        const resp = await fetch(url);
        const result = await resp.text();
        return result;
        console.log(result)
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('submitBtn').addEventListener('click', () => {
    let isOk = registerValidation();
    if (isOk == 0) {
        alert('이메일을 입력해주세요.');
        return false;
    } else if (isOk == 1) {
        alert('비밀번호를 입력해주세요.');
    } else if (isOk == 2) {
        alert('비밀번호 확인을 입력해주세요.');
        return false;
    } else if (isOk == 3) {
        alert('닉네임을 입력해주세요.');
        return false;
    } else if (isOk == 4) {
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    } else if (!confirmId) {
        alert('아이디를 중복확인해주세요.');
    } else {
        alert('회원가입되었습니다. 로그인해주세요.');
        document.getElementById('form').submit();
    }
});

document.addEventListener('input', (e) => {
    if (e.target.id == 'pwdConfirm' || e.target.id == 'pwd') {
        const pwd = document.getElementById('pwd').value;
        const pwdConfirm = document.getElementById('pwdConfirm').value;
        const span = document.getElementById('confirmText');
        if (pwd != pwdConfirm) {
            span.setAttribute("style", "color: red");
            span.innerText = '비밀번호가 일치하지 않습니다.';
        } else {
            span.setAttribute("style", "color: blue");
            span.innerText = '비밀번호가 일치합니다.';
        }
    }
})

function registerValidation() {
    const email = document.getElementById('email').value;
    const pwd = document.getElementById('pwd').value;
    const pwdConfirm = document.getElementById('pwdConfirm').value;
    const nickName = document.getElementById('nickName').value;
    if (email == null || email == "") {
        return 0;
    } else if (pwd == null || pwd == "") {
        return 1;
    } else if (pwdConfirm == null || pwdConfirm == "") {
        return 2;
    } else if (nickName == null || nickName == "") {
        return 3;
    } else if (pwd != pwdConfirm) {
        return 4;
    } else {
        return 5;
    }
}


