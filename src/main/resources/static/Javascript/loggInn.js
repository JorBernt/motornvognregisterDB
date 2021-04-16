$("#loggInnKnapp").click(()=> {
    const kunde = {
        navn : $("#brukernavnFelt").val(),
        passord : $("#passordFelt").val()
    }
    $.get("/logginn", kunde, innlogget => {
        if(innlogget) {
            window.location.href = "/"
        }
        else {
            $("#feilMelding").val("Feil brukernavn eller passord")
        }
    })
        .fail(()=> {
            $("#feilMelding").val("Serverfeil - prøv igjen senere")
        })
})