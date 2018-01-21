$(".delbtn").click(function() {
	account = $(this).parent().siblings(".account").text();
	if (confirm("确定要删除'" + account + "'么?")) {
		if (confirm("真的确定么?删除之后就无法复原了")) {
			window.location = '/knight/setting/del/' + account;
		}
	}
});

$(".upbtn").click(function() {
	memberId = $(this).parent().siblings(".id").text();
	if (confirm("确定要提升'" + memberId + "'为管理员么?")) {
		if (confirm("真的确定么?提升后可就无法降低了")) {
			window.location = '/admin/setting/up/' +memberId;
		}
	}
});

$(".delfile").click(function() {
	var name = $(this).parents(".file").find(".filename").text();
	if (confirm("确定删除" + name + "么?")) {
		var location = $(document).scrollTop();
		$.post("/knight/delfile", {
				name: name
			},
			function(data, status) {
				document.write(data);
				document.close();
				$(document).scrollTop(location);
			});
	}
});

$('.m-knight-list').Tabledit({
    url: '/admin/setting/update/',
    columns: {
        identifier: [0, 'id'],
        editable: [[5, 'email']]
    },
    onAjax: function(action, serialize){
        // email validation
        // http://stackoverflow.com/questions/46155/validate-email-address-in-javascript
        var email_pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        // decode and split the query sting
        var queries = decodeURIComponent(serialize).split('&');
        // loop over, and split key/value
        for (var i=0; i< queries.length; i++){
            var pair = queries[i].split('=');
            // regex check again email's value
            if (pair[0] == "email"){
                var is_email_or_empty = email_pattern.test(pair[1]) || pair[1] == "";
                // fing this input is not very elegent
                var email_input = $('.m-knight-list').find('.tabledit-input:visible');
                // set a visual tip 
                if (is_email_or_empty)
                   email_input.parent().removeClass('has-error'); 
                else
                   email_input.focus().parent().addClass('has-error');
                // return false will prevent the ajax
                return is_email_or_empty
            }
        }
    }
})

