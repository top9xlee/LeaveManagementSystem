function send(){
    var arr= document.getElementsByTagName('input');
    var name = arr[0].value;
    var age = arr[1].value;
    var title = arr[2].value;
    if(name == ""|| age == "" ){
        alert("please fill all fields");
        return;
    };
    if(!isNaN(title)){
        alert("not a number");
        return;
    }
}
function dayin(){
    var dayIn = document.getElementById('dayIn');
    console.log(dayIn)
    document.getElementById("end").set
}