$(()=> {
    hentBiler(false);
    hentAlle();
    endreFelt();
})

const endreFelt = () => {
    const id = window.location.href.split("=")[1];
    $.get("/hentEn?id="+id, kunde => {
        $("#personnummerInput").val(kunde.personNr);
        $("#navnInput").val(kunde.navn);
        $("#adresseInput").val(kunde.adresse);
        $("#kjennetegnInput").val(kunde.kjennetegn);
        $("#merke").val(kunde.bilmerke);
        oppdaterType(kunde.bilmerke, kunde.biltype)
    })
}

$("#endreKnapp").click(()=>{
    const id = window.location.href.split("=")[1];
    if(valider()) {
        const kunde = lagKundeObjekt(id)
        $.post("/endreKunde", kunde, ()=> {
            window.location.href = "/";
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
    return pnr && navn && adresse && kjennetegn;
}

