const { Router } = require('express')
const rooteable = Router()
const OCLL = require('../controllers/obrasControllers')

rooteable.get('/', OCLL.obtenerObra)
rooteable.get('/obtAct/:idOb', OCLL.obtenerOneObra)
rooteable.post('/crear', OCLL.guardarObras)
rooteable.put('/actualizar/:idOb', OCLL.actualizaObra)
rooteable.delete('/eliminar/:idOb', OCLL.eliminaObra)
module.exports = rooteable;