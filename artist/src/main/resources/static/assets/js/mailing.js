$('#addMailingMemberForm').on('submit', function (e) {
    e.preventDefault();
    $.ajax({
        url: "/mail/member/add",
        type: 'POST',
        async: true,
        data: {
            name: $('#mailingName').val(),
            email: $('#mailingEmail').val()
        },
        success: function () {
            $('#mailingModal').modal('hide');
        },
        error: function () {
            $('addMailingMemberError').text('Wystąpił błąd, proszę spróbować później, a jeśli sytuacja będzie sie powtarzać zgłosić autorowi strony.')
        }
    });
});