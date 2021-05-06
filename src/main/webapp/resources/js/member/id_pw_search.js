$(document).ready(function(){

    // 비밀번호 찾기
    $("#btn_PW_Serch").on("click", function(){
        
        var userInfo = {mbme_id:$("#mbme_id").val() , mbme_name: $("#mbme_name").val()};

        $.ajax({
            url: '/member/find_pwd',
            type: 'post',
            data: userInfo,
            dataType: 'text',
            success: function(data){
                // 비밀번호 출력작업

                if(data == "Success"){
                   alert("가입하신 메일로 비밀번호를 전송했습니다.");

                }else{
                    $("#result").html("입력정보가 틀립니다.");
                }
            }
        });

    });

    // 아이디 찾기 : 화면출력
    $("#btn_ID_Serch").on("click", function(){

        $.ajax({
            url: '/member/find_id',
            type: 'post',
            data: { mbme_name: $("#mbme_name").val()},
            dataType: 'text',
            success: function(data){

                if(data != ""){
                    $("#result").html($("#mbme_name").val() + "님의 아이디는? " + data + " 입니다.")
                }else{
                    $("#result").html("입력정보가 틀립니다.");
                }

                $("#mbme_name").val("");
            }
        })

    });

});