
$(document).ready(function(){
    var quantity = 1;
    $("#btn-minus").click(function(){
        if(quantity > 1){
            quantity--;
            $("#var-value").text(quantity);
            $("#product-quanity").val(quantity);
        }
    });
    $("#btn-plus").click(function(){
        quantity++;
        $("#var-value").text(quantity);
        $("#product-quanity").val(quantity);
    });
});
$('#btn-size').click(function(){
      var this_val = $(this).html();
      $("#product-size").val(this_val);
      $(".btn-size").removeClass('btn-secondary');
      $(".btn-size").addClass('btn-success');
      $(this).removeClass('btn-success');
      $(this).addClass('btn-secondary');
      return false;
    });

