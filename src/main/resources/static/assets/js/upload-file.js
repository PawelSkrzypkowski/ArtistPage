var Upload = function (file, url, additionalData) {
    this.file = file;
    this.url = url;
    this.additionalData = additionalData;
};

Upload.prototype.getType = function() {
    return this.file.type;
};
Upload.prototype.getSize = function() {
    return this.file.size;
};
Upload.prototype.getName = function() {
    return this.file.name;
};
Upload.prototype.doUpload = function (successCallback) {
    var that = this;
    var formData = new FormData();
    var url = this.url;

    // add assoc key values, this will be posts values
    if(Array.isArray(this.file)){
        var array = [];
        $.each(this.file, function (idx, val) {
            array.push({file: val, name: val.name});
        });
        formData.append("files", array);
    } else {
        formData.append("file", this.file, this.getName());
    }
    formData.append("upload_file", true);
    if(this.additionalData && Array.isArray(this.additionalData)){
        $.each(this.additionalData, function (idx, val) {
            formData.append(val.name, val.value);
        });
    }

    $.ajax({
        type: "POST",
        url: url,
        xhr: function () {
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', that.progressHandling, false);
            }
            return myXhr;
        },
        success: successCallback,
        error: function (error) {
            // handle error
        },
        async: true,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 60000
    });
};

Upload.prototype.progressHandling = function (event) {
    var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progress_bar_id = "#progress-wrp";
    if (event.lengthComputable) {
        percent = Math.ceil(position / total * 100);
    }
    // update progressbars classes so it fits your code
    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
    $(progress_bar_id + " .status").text(percent + "%");
};