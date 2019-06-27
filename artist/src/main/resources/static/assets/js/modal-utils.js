var Modal = function () {};
Modal.getAddBlogModal = function () {
    var url = context + 'admin/modal/addBlogPage';
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#addBlogModal").modal('show');
        }
    });
};

Modal.getEditBlogModal = function (id) {
    var url = context + 'admin/modal/editBlogPage/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#addBlogModal").modal('show');
        }
    });
};

Modal.getDeleteBlogModal = function (id) {
    var url = context + 'admin/modal/deleteBlogPage/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#deleteBlogModal").modal('show');
        }
    });
};

Modal.getMailingModal = function () {
    var url = context + 'modal/mailing';
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#mailingModal").modal('show');
        }
    });
};

Modal.getSendMailModal = function (id) {
    var url = context + 'admin/modal/sendMail/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#sendMailModal").modal('show');
        }
    });
};

Modal.getDeleteMailMemberModal = function (id) {
    var url = context + 'admin/modal/deleteMailMember/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#deleteMailMemberModal").modal('show');
        }
    });
};

Modal.getSendMailToAllModal = function () {
    var url = context + 'admin/modal/sendMultipleMail';
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#sendMailModal").modal('show');
        }
    });
};

Modal.getAddCategoryModal = function () {
    //TODO: tylko ta metoda i link na stronie
    var url = context + 'admin/modal/getAddCategoryModal';
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#addCategoryModal").modal('show');
        }
    });
};

Modal.getDeleteCategoryModal = function (id) {
    var url = context + 'admin/modal/deleteCategory/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#deleteCategoryModal").modal('show');
        }
    });
};

Modal.getAddCategoryElementModal = function () {
    //TODO: tylko ta metoda i link na stronie
    var url = context + 'admin/modal/getAddCategoryModal';
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#addCategoryModal").modal('show');
        }
    });
};

Modal.getDeleteCategoryElementModal = function (id) {
    var url = context + 'admin/modal/deleteCategoryElement/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#deleteCategoryElementModal").modal('show');
        }
    });
};

Modal.getEditCategoryModal = function (id) {
    //TODO: tylko ta metoda i link na stronie
    var url = context + 'admin/modal/editCategoryModal/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#editCategoryModal").modal('show');
        }
    });
};

Modal.getEditCategoryElementModal = function (id) {
    //TODO: tylko ta metoda i link na stronie
    var url = context + 'admin/modal/editCategoryElementModal/' + id;
    $.ajax({
        url: url,
        success: function (data) {
            $("#modal-holder").html(data);
            $("#editCategoryElementModal").modal('show');
        }
    });
};