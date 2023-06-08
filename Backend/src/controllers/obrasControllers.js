const OCLL = {}
const ObM = require('../models/obrasModel')

OCLL.obtenerObra = async (req, res) => {
    const getObras = await ObM.find()
    res.status(200).json(getObras)
}
OCLL.obtenerOneObra = async (req, res) => {
    //console.log(req.params.idOb)
    const getOneObra = await ObM.findOne({ idObra: req.params.idOb })
    if (getOneObra == null) {
        res.status(404).json({message: 'Obra no encontrada'})
    } else res.status(200).json(getOneObra)
}
OCLL.guardarObras = async (req, res) => {
    const idObras = await ObM.findOne({ idObra: req.body.idObra })
    if (idObras == null) {
        const guardaObra = new ObM(req.body)
        await guardaObra.save()
        res.status(200).json({ menssage: 'Obra Guardada' })
    } else {
        res.status(404).json({ message: 'Ya existe la identificacion de esta obra, Intente de nuevo.' })
    }
}
OCLL.actualizaObra = async (req, res) => {
    const actualizame = await ObM.findOneAndUpdate({ idObra: req.params.idOb }, req.body)
    if (actualizame != null) res.status(200).json({ menssage: 'Obra Actualizada' })
    else res.status(404).json({ message: 'Error al eliminar' })
}
OCLL.eliminaObra = async (req, res) => {
    const eliminame = await ObM.findOneAndDelete({ idObra: req.params.idOb })
    if (eliminame != null) res.status(200).json({ menssage: 'Obra Eliminada' })
    else res.status(404).json({ message: 'Error al eliminar' })
}
module.exports = OCLL;