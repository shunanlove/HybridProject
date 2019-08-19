let local_storage = window.localStorage;
let session_storage = window.sessionStorage;

export default {
    test: function () {
        if (local_storage) {
            console.log('这个浏览器支持localStorage');
        } else {
            console.log('这个浏览器不支持localStorage');
        }

        if (session_storage) {
            console.log('这个浏览器支持sessionStorage');
        } else {
            console.log('这个浏览器不支持sessionStorage');
        }
    },

    setSessionData: function (k, v) {
        session_storage.setItem(k, v);
    },

    getSessionData: function (k) {
        return session_storage.getItem(k);
    },

    setSessionJson: function (k, obj) {
        session_storage.setItem(k, JSON.stringify(obj));
    },

    getSessionJson: function (k) {
        return JSON.parse(session_storage.getItem(k));
    },

    removeSession: function (k) {
        session_storage.removeItem(k);
    },

    setLocalData: function (k, v) {
        local_storage.setItem(k, v);
    },

    getLocalData: function (k) {
        return local_storage.getItem(k);
    },

    setLocalJson: function (k, obj) {
        local_storage.setItem(k, JSON.stringify(obj));
    },

    getLocalJson: function (k) {
        return JSON.parse(local_storage.getItem(k));
    },

    removeLocal: function (k) {
        local_storage.removeItem(k);
    },

    clearSession: function () {
        for (let i = session_storage.length; i >= 0; i--) {
            session_storage.removeItem(session_storage.key(i));
        }
    },

    clearLocal: function () {
        for (let i = local_storage.length; i >= 0; i--) {
            local_storage.removeItem(local_storage.key(i));
        }
    }
}