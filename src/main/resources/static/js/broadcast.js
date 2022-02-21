
function Broadcast() {
	this.stomp = null;
	this.from = null;
	this.onConnected = function(ok) {};
	this.onMessage = function(text) {};
}

Broadcast.prototype.connect = function(from) {
    var socket = new SockJS('/socket');
    var stomp = this.stomp = Stomp.over(socket);  
	var plug = this.onConnected;
	var trig = this.onMessage;
	this.from = from;
    stomp.connect({}, function(frame) {
        plug(true);
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/main', function(messageOutput) {
			var event = JSON.parse(messageOutput.body);
			var from = event.from;
			var text = event.text;
            trig(from, text);
        });
    });
}
           
Broadcast.prototype.disconnect = function() {
	var plug = this.onConnected;
	var stomp = this.stomp;
    if (stomp != null) {
        stomp.disconnect();
    }
    plug(false);
    console.log("Disconnected");
}
           
Broadcast.prototype.send = function(text) {
	var stomp = this.stomp;
    stomp.send("/app/main", {}, JSON.stringify({'from':this.from, 'text':text}));
}
