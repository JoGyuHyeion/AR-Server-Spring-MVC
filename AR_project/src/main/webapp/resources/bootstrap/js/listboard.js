function Check()
{
    if (Form.keyword.value.length < 1) {
        alert("검색어를 입력하세요.");
        Form.keyword.focus();
        return false;
    }
}
function boardWrite(pageNum)
{
    //  alert(pageNum);
    location.href = "./upload.jsp?pageNum=" + pageNum;
}