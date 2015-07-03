$(".form-button").on("click", function() {
  var email = $("#email").val();
  var name = $("#name").val();

  if(!validateEmail(email) || !validateName(name)) {
    $("#error").css("display","inline-block");
    $("#success").css("display","none");
  } else {
    $("#success").css("display","inline-block");
    $("#error").css("display","none");
    $.get("/send?email="+email + "&name=" + name);
  }

});

function validateEmail($email) {
 var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 return $email && emailReg.test( $email );
}

function validateName($name) {
  return $name;
}

$(".close-warning").on("click", function() {
  $(".notices").hide("fast");
});
