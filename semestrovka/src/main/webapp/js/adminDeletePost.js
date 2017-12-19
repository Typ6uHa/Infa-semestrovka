$(document).ready(function(){
    $(document).on('click', '.glyphicon-remove', function(e){

        var topic_id = $(this).attr('value');

        $.ajax({
            url: "/ajax-admindeletepost",
            data: {"id" : topic_id},
            success: function (result) {
                $(".topic" + topic_id).remove();
            }
        });
        window.location = "http://localhost:8080/";

    });
});
