function Check() {
    if (Write.name.value.length < 1) {
        alert("작성자를 입력하세요.");
        Write.name.focus();
        return false;
    }

    if (Write.pass.value.length < 1) {
        alert("비밀번호를 입력하세요.");
        Write.pass.focus();
        return false;
    }

    if (Write.email.value.indexOf("@") + "" == "-1" ||
            Write.email.value.indexOf(".") + "" == "-1" ||
            Write.email.value == "") {
        alert("E-mail을 입력하세요.");
        Write.email.focus();
        return false;
    }

    if (Write.title.value.length < 1) {
        alert("글제목을 입력하세요.");
        Write.write_title.focus();
        return false;
    }

    if (Write.contents.value.length < 1) {
        alert("글내용을 입력하세요.");
        Write.content.focus();
        return false;
    }
}