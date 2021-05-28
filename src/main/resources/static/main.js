let ws = new SockJS('https://mac21-chat.herokuapp.com/ws');
let stomp = Stomp.over(ws);
stomp.debug = () => {
};

stomp.connect();

const userIdInputRef = document.querySelector('#userIdInput');
const sessionIdInputRef = document.querySelector('#sessionIdInput');
const messageInputRef = document.querySelector('#messageInput');
let videoRef = document.getElementById('video');
const outputRef = document.querySelector('#output');

let userId;
let sessionId;
let isAdmin;

// ui handlers
function onConnectAsGuestButtonClick() {
    console.log('onConnectAsGuestButtonClick')
    userId = userIdInputRef.value;
    sessionId = sessionIdInputRef.value;
    isAdmin = false;
    connectToSession();
}

function onConnectAsAdminButtonClick() {
    console.log('onConnectAsAdminButtonClick')
    userId = userIdInputRef.value;
    sessionId = sessionIdInputRef.value;
    isAdmin = true;
    connectToSession();
}

function onSendButtonClick() {
    console.log('onSendButtonClick')
    const message = messageInputRef.value;
    sendMessage(message);
}

function onPlayVideoButtonClick() {
    console.log('onPlayVideoButtonClick')
    if (isAdmin) {
        videoRef.play().catch(reason => console.log(reason));
        sendMessage({type: 'status', status: 'played', time: videoRef.currentTime});
    }
}

function onPauseVideoButtonClick() {
    console.log('onPauseVideoButtonClick')
    if (isAdmin) {
        videoRef.pause();
        sendMessage({type: 'status', status: 'paused', time: videoRef.currentTime});
    }
}

function onVideoTimeSeeked() {
    console.log('onVideoTimeUpdate')
    if (isAdmin) {
        const status = videoRef.paused ? 'paused' : 'played';
        sendMessage({type: 'status', status, time: videoRef.currentTime});
    }
}

// logic
function connectToSession() {
    console.log('connectToSession')
    const topic = '/sessions/' + sessionId;
    stomp.subscribe(topic, function (message) {
        onMessageReceived(JSON.parse(message.body));
    });
    sendMessage({type: 'join'});
}

function onMessageReceived(message) {
    console.log('onMessageReceived')
    console.log(message)
    if (message.type === 'join') {
        console.log('join');
        if (isAdmin) {
            const status = videoRef.paused ? 'paused' : 'played';
            const data = {type: 'info', status, time: videoRef.currentTime};
            sendMessage(data);
        }
    }
    if (message.type === 'status') {
        console.log('status');
        if (!isAdmin) {
            const time = message.time;
            const status = message.status;
            console.log(time, status);
            videoRef.currentTime = time;
            if (status === 'played') {
                videoRef.play().catch(reason => console.log(reason));
            }
            if (status === 'paused') {
                videoRef.pause();
            }
        }
    }
}

function sendMessage(message) {
    console.log('sendMessage');
    console.log(message);
    const topic = '/sessions/' + sessionId;
    stomp.send(topic, {}, JSON.stringify(message));
}


/////////////////////////////////////////////////////////////////
if (Hls.isSupported()) {

    let hls = new Hls();
    hls.loadSource('https://moviescontainerhls.s3-us-west-2.amazonaws.com/sintel/sintel.m3u8');
    hls.attachMedia(video);
    hls.on(Hls.Events.MANIFEST_PARSED, function () {
        video.play();
    });
}