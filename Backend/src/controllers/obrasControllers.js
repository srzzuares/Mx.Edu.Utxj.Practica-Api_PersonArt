const OCLL = {}
const ObM = require('../models/obrasModel')

OCLL.obtenerObra = async (req, res) => {
    const getObras = await ObM.find()
    res.json(getObras)
}
OCLL.obtenerOneObra = async (req, res) => {
    //console.log(req.params.idOb)
    const getOneObra = await ObM.findOne({ idObra: req.params.idOb })
    if (getOneObra == null) {
        res.json({status: 'Obra no encontrada'})
    } else res.status(200).json(getOneObra)
}
OCLL.guardarObras = async (req, res) => {
    const idObras = await ObM.findOne({ idObra: req.body.idObra })
    if (idObras == null) {
        const guardaObra = new ObM(req.body)
        await guardaObra.save()
        res.json({ status: 'Obra Guardada' })
    } else {
        res.json({ status: 'Ya existe la identificacion de esta obra, Intente de nuevo.' })
    }
}
OCLL.actualizaObra = async (req, res) => {
    const actualizame = await ObM.findOneAndUpdate({ idObra: req.params.idOb }, req.body)
    if (actualizame != null) res.json({ status: 'Obra Actualizada' })
    else res.json({ status: 'Error al actualizar' })
}
OCLL.eliminaObra = async (req, res) => {
    const eliminame = await ObM.findOneAndDelete({ idObra: req.params.idOb })
    if (eliminame != null) res.json({ status: 'Obra Eliminada' })
    else res.json({ status: 'Error al eliminar' })
}
module.exports = OCLL;