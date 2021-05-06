$(document).ready(function() {

    var form = $("#joinForm");

    // 아이디 중복체크 기능
    var isCheckId = "false";
    
    $("#btn_checkId").on("click", function(){

        if($("mbme_id").val()=="" || $("#mbme_id").val()== null){
            $("#id_error").html("아이디를 먼저 입력해주세요.");
            return;
        }

        var mbme_id = $("#mbme_id").val();

        $.ajax({
            url: '/member/checkIdOverlap',
            type: 'post',
            dataType: 'text',
            data: {mbme_id : mbme_id},
            success : function(data){
                if(data== 'Success'){
                    $("#id_error").css("color", "blue");
                    $("#id_error").html("사용가능한 아이디 입니다.");

                    isCheckId = "true";
                }else{
                    $("#id_error").html("이미 존재하는 아이디입니다.");
                }
            }
        });
    });


    $("#btn_submit").on("click", function(){
		
		
        form.submit();

    });


    
});