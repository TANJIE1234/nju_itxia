$(".delbtn").click(function() {
    if (confirm("确定要删除本次请求么?")) {
        window.location = '/helper/deleteorder';
    }
});

$('.content').keydown(function(e) {
    if (e.ctrlKey && e.which == 13 || e.which == 10) {
        btn = $(this).parents(".reply").find(".subbtn").click();
    }
});

function uploadfile() {
    var oid = $(".filebtn").attr("order-id");
    var path = $("#uploadfile").val();
    var index = path.lastIndexOf(".");
    var ext = path.substr(index).toLowerCase();
    var myDate = new Date();
    var p = myDate.getTime();
    var name = oid + "-" + p + ext;
    $.post("/helper/upload", {
            oid: oid,
            name: name,
            ext: ext
        },

        function(data, status) {
            $("#formkey").val(name);
            $(".fileform").submit();
        });
}

$('.filebtn').click(function(e) {
    e.preventDefault()
    var path = $("#uploadfile").val();
    if (path !== ""){
        $(this).html('<span class="glyphicon glyphicon-refresh"></span>正在上传，请稍候');
        $(this).attr("disabled", "disabled");
        uploadfile();
    }
});

$(".replybtn").click(function() {
    $(".reply").toggle("slow");
});

$("#uploadfile").change(function(){
  toggleUploadBtn();
});

$(function(){
  toggleUploadBtn();
})


function toggleUploadBtn(){
  var files = $('#uploadfile').prop('files');
  console.log(files.length);
  if (files.length == 0){
    $('.filebtn').hide();
  }
  else{
    $('.filebtn').show()
  }
}

// http://stackoverflow.com/questions/13704789/can-i-use-complex-html-with-twitter-bootstraps-tooltip
$('.panel.m-tips').tooltip({
    selector: "a[rel=tooltip]"
})
