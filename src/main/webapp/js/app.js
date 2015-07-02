$(".form-button").on("click", function() {
  var email = $(".email-field").val();
  if(!validateEmail(email)) {
    $("#error").css("display","inline-block");
    $("#success").css("display","none");
  } else {
    $("#success").css("display","inline-block");
    $("#error").css("display","none");
    $.get("/send?email="+email);
  }

});

function validateEmail($email) {
 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 return $email && emailReg.test( $email );
}


$(".close-warning").on("click", function() {
  $(".notices").hide("fast");
});
