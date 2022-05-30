require.config({
    paths:{
        "jquery":"../js/jquery-1.6.2.min",
    },
});

require(['jquery','register'],function(jquery,register){
    $(function(){
        register.regisBtn();
    });
});