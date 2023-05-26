// -------------------------------------- 새글쓰기
const writebtn = document.querySelector('#writebtn');
writebtn?.addEventListener('click', () => {
    const pdsfrm = document.forms.pdsfrm;
    if(pdsfrm.title.value === '')    alert("제목을 입력하세요");
    else if(pdsfrm.content.value === '') alert("본문을 입력하세요");
    else if(grecaptcha.getResponse() === '') alert("자갑방 필수!");
    else{
        pdsfrm.method='post';
        pdsfrm.enctype ='multipart/form-data';
        pdsfrm.submit();
    }
})

// -------------------------------------- 자료실 리스트
const newbtn = document.querySelector('#newbtn');   // 새글쓰기 버튼
newbtn?.addEventListener('click', () => {
    location.href="/pds/write";
})


// view
const listbtn = document.querySelector('#listbtn');
listbtn?.addEventListener('click', () => {
    location.href = "/pds/list?cpg=1";
})

// ------------------------------------- 댓글 작성
const rpnewbtn = document.querySelector('#rpnewbtn');
rpnewbtn?.addEventListener('click', () => {
    const rpfrm = document.forms.rpfrm;
    if(rpfrm.reply.value == "")alert("내용을 입력하세요");
    else{
        rpfrm.method = 'post';
        rpfrm.action = '/pds/replyok?pno=' + rpfrm.pno.value;
        rpfrm.submit();
    }
})

let modal = null;
// [추가] 링크 클릭시 대댓글 작성 모달 띄우기
const showComment = (refno) => {
    const frefno = document.querySelector('#refno');
    const cmtModal = document.querySelector("#cmtModal");

    frefno.value = refno;

    let mymodal = null;
    try{
        mymodal = new bootstrap.Modal(cmtModal, {});
        modal = mymodal;
    }catch (e){}

    mymodal.show();
}

const cmtbtn = document.querySelector("#cmtbtn");
cmtbtn?.addEventListener('click', () => {
    const cmtfrm = document.forms.cmtfrm;
    if(cmtfrm.reply.value === '') alert("댓글을 작성하세요");
    else if(cmtfrm.userid.value === '') alert("작성자가 없어요");
    else if(cmtfrm.pno.value === '') alert("본문글 번호가 없어요");
    else if(cmtfrm.refno.value === '') alert("댓글번호가 없어요");
    else{
        cmtfrm.method = 'post';
        cmtfrm.action = '/pds/rreplyok';
        cmtfrm.submit();
    }
})