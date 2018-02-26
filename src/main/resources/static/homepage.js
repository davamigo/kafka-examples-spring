$(function() {
    'use strict';

    var refreshConsumers = function () {
        loadConsumerData("auto", "#consumer1_ta");
        loadConsumerData("manual", "#consumer2_ta");
    };

    var loadConsumerData = function (consumerName, idTextArea) {
        var $textArea = $(idTextArea);
        $textArea.val("");
        $.get('api/message/?consumer=' + consumerName)
            .done(function (messages) {
                var text = "";
                for (var index in messages) {
                    text += messages[index].text + "\n";
                }
                $textArea.val(text);
            }).fail(function (xhr) {
            console.log(xhr.responseJSON.status + ' - ' + xhr.responseJSON.error + ' - ' + xhr.responseJSON.path);
            $textArea.val("Error!");
        });
    };

    refreshConsumers();

    $("form").on('submit', function (ev) {
        ev.preventDefault();
    });

    $('#submit_button').click(function () {
        $.post('/api/message', {
            text: $('#text_ctrl').val(),
            key: $('#key_ctrl').val()
        }).done(function () {
            setTimeout(refreshConsumers, 500);
        }).fail(function (xhr) {
            console.log(xhr.responseJSON.status + ' - ' + xhr.responseJSON.error + ' - ' + xhr.responseJSON.path);
        });
    });
});
