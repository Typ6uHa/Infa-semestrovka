$(document).ready(function(){
    $(document).on('click', '.glyphicon-remove', function(e){

        var comment_id = $(this).attr('value');

        $.ajax({
            url: "/ajax-comment",
            data: {"id" : comment_id},
            success: function (result) {
                $(".comment" + comment_id).remove();
            }
        })

    });
});

