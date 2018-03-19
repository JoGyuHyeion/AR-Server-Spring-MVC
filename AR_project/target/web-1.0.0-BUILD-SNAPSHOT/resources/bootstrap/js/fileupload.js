var fileExtentionRange = '.zip .rar .tar .pdf .doc .docx .xls .xlsx .ppt .pptx .hwp .jpg .jpeg .png';
var MAX_SIZE = 30; // MB


var change_handler = function (id) {
    var input = $(this);
    
    alert("change handler :" + input.val());
    alert("change handler :" + input.val());
    alert("change handler :" + input.val());

    if (navigator.appVersion.indexOf("MSIE") !==   -1) { // IE
        var label = input.val();

        input.trigger('fileselect', [1, label, 0]);
    } else {
        var label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var numFiles = input.get(0).files ? input.get(0).files.length : 1;
        var size = input.get(0).files[0].size;

        //input.trigger('fileselect', [numFiles, label, size]);
        file_selector (id, label, size);
    }
};

$(document).on('change', '.btn-file :file', function () {
    var input = $(this);
    
    alert("on change" + input.val());

    if (navigator.appVersion.indexOf("MSIE") !== -1) { // IE
        var label = input.val();

        input.trigger('fileselect', [1, label, 0]);
    } else {
        var label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var numFiles = input.get(0).files ? input.get(0).files.length : 1;
        var size = input.get(0).files[0].size;

        input.trigger('fileselect', [numFiles, label, size]);
        //file_selector (id, label, size);
    }
});
/*
function () {
    var input = $(this);

    if (navigator.appVersion.indexOf("MSIE") != -1) { // IE
        var label = input.val();

        input.trigger('fileselect', [1, label, 0]);
    } else {
        var label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        var numFiles = input.get(0).files ? input.get(0).files.length : 1;
        var size = input.get(0).files[0].size;

        input.trigger('fileselect', [numFiles, label, size]);
    }
});
*/

var file_selector = function (id, label, size) {
    alert("on file select");
    var target_obj_name = '#_'+ id; // bug fixed by kiun91
    
    
    //$('#attachmentName').attr('name', 'attachmentName'); // allow upload.
    $(target_obj_name).attr('name', target_obj_name); // allow upload.
    
    
    
    var postfix = label.substr(label.lastIndexOf('.'));
    if (fileExtentionRange.indexOf(postfix.toLowerCase()) > -1) {
        if (size > 1024 * 1024 * MAX_SIZE) {
            alert('max size：<strong>' + MAX_SIZE + '</strong> MB.');

            //$('#attachmentName').removeAttr('name'); // cancel upload file.
            $(target_obj_name).removeAttr('name'); // cancel upload file.
            
        } else {
            //$('#_attachmentName').val(label);

            //$('#_'+event.target.id).val(label) ;
            $(target_obj_name).val(label) ;
        }
    } else {
        alert('파일 확장자는：' + fileExtentionRange + '만 입력 가능합니다.');

        //$('#attachmentName').removeAttr('name'); // cancel upload file.
        
        $(target_obj_name).removeAttr('name'); // cancel upload file.
    }
};
    

    


$('.btn-file :file').on('fileselect', function (event, numFiles, label, size) {
    alert("on file select");
    var target_obj_name = '#_'+event.target.id; // bug fixed by kiun91
    
    
    //$('#attachmentName').attr('name', 'attachmentName'); // allow upload.
    $(target_obj_name).attr('name', 'attachmentName'); // allow upload.
    
    
    
    var postfix = label.substr(label.lastIndexOf('.'));
    if (fileExtentionRange.indexOf(postfix.toLowerCase()) > -1) {
        if (size > 1024 * 1024 * MAX_SIZE) {
            alert('max size：<strong>' + MAX_SIZE + '</strong> MB.');

            //$('#attachmentName').removeAttr('name'); // cancel upload file.
            $(target_obj_name).removeAttr('name'); // cancel upload file.
            
        } else {
            //$('#_attachmentName').val(label);

            //$('#_'+event.target.id).val(label) ;
            $(target_obj_name).val(label) ;
        }
    } else {
        alert('파일 확장자는：' + fileExtentionRange + '만 입력 가능합니다.');

        //$('#attachmentName').removeAttr('name'); // cancel upload file.
        
        $(target_obj_name).removeAttr('name'); // cancel upload file.
    }
});

