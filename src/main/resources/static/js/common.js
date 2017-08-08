/**
 * Created by admin on 2017/8/8.
 */
function cleanSession(){
    $.post("../user/clearSession",{},function(data){
        document.location.reload();
    })
}
