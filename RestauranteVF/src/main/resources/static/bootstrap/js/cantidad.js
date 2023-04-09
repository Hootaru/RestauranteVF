
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

