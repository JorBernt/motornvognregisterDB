$("#loggInnKnapp").click(()=> {
    const navn = $("#brukernavnFelt").val()
    const passord = $("#passordFelt").val()

    if(validerInnlogging(navn, passord)) {
    const kunde = {
        navn : navn,
        passord : passord
    }
    $.get("/logginn", kunde, innlogget => {
        if(innlogget) {
            window.location.href = "/"
        }
        else {
            $("#feilMelding").html("Feil brukernavn eller passord")
            $("#feilMelding").css({"color":"red"})
        }
    })
        .fail(()=> {
            $("#feilMelding").html("Serverfeil - prøv igjen senere")
            $("#feilMelding").css({"color":"red"})
        })
    }
    else {
        $("#feilMelding").html("Du må skrive noe inn i feltene")
        $("#feilMelding").css({"color":"red"})
    }
})

$(document).bind('keypress', e => {
    if(e.keyCode==13){
        $('#loggInnKnapp').trigger('click');
    }
});