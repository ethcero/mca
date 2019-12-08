const { spawn } = require('child_process')
function exec(serviceName, command, cwd){
console.log(`Stated service [${serviceName}]`)
let cmd = spawn(command, [], { cwd, shell: true })
cmd.stdout.on('data', function(data){
process.stdout.write(`[${serviceName}] ${data}`)
})
cmd.stderr.on('data', function(data){
process.stderr.write(`[${serviceName}] ${data}`)
})
}
exec('externalservice', 'node src/app.js', './externalservice')
exec('server','node src/app.js', './server')
exec('worker','mvn spring-boot:run', './worker')