$(()=> {
    hentBiler(true);
})

$("#registrerKnapp").click(() => {
    if(valider()) {
        const bil = lagKundeObjekt(0);

        $.post("/lagre", bil, () => {
            window.location.href = "index.html";
        })
            .fail(jqXHR => {
                const json = $.parseJSON(jqXHR.responseText)
                $("feilmeldingFelt").html(json.message)
            })
    }
})

const valider = () => {
    const pnr = validerPNR($("#personnummerInput").val());
    const navn = validerNavn($("#navnInput").val());
    const adresse = validerAdresse($("#adresseInput").val())
    const kjennetegn = validerKjennetegn($("#kjennetegnInput").val())
    const passord = validerPassord($("#passord").val())
    return pnr && navn && adresse && kjennetegn && passord;
}


