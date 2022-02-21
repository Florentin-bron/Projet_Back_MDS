function move() {
    var elem = document.getElementById("myBar");
    var width = 20;
    var id = setInterval(frame, 50);
    function frame() {
        if (width >= 80) {
            clearInterval(id);
        } else {
            width++;
            elem.style.width = width + '%';
            elem.innerHTML = width * 1 + '%';
        }
    }
}