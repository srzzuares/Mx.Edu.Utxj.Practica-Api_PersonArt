const OCLL = {}
const ObM = require('../models/obrasModel')

OCLL.obtenerObra = async (req, res) => {
    const getObras = await ObM.find()
    res.status(200).json({ message: 'Obras Encontradas', data: getObras })
}
OCLL.obtenerOneObra = async (req, res) => {
    //console.log(req.params.idOb)
    const getOneObra = await ObM.findOne({ idObra: req.params.idOb })
    if (getOneObra == null) {
        res.status(404).json({message: 'Obra no encontrada'})
    } else res.status(200).json({ message: 'Obras Encontradas', data: getOneObra })
}
OCLL.guardarObras = async (req, res) => {
    const idObras = await ObM.findOne({ idObra: req.body.idObra })
    if (idObras == null) {
        const guardaObra = new ObM(req.body)
        await guardaObra.save()
        res.status(200).json({ menssage: 'Obra Guardada', data: guardaObra })
    } else {
        res.status(404).json({ message: 'Ya existe la identificacion de esta obra, Regrese la anterio pagina.' })
    }
}
OCLL.actualizaObra = async (req, res) => {
    const actualizame = await ObM.findOneAndUpdate({ idObra: req.params.idOb }, req.body)
    if (actualizame != null) res.status(200).json({ menssage: 'Obra Actualizada', data: actualizame })
    else res.status(404).json({ message: 'Error al eliminar' })
}
OCLL.eliminaObra = async (req, res) => {
    const eliminame = await ObM.findOneAndDelete({ idObra: req.params.idOb })
    if (eliminame != null) res.status(200).json({ menssage: 'Obra Eliminada', data: eliminame })
    else res.status(404).json({ message: 'Error al eliminar' })
}
module.exports = OCLL;