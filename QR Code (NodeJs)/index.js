/* 
1. Use the inquirer npm package to get user input.
2. Use the qr-image npm package to turn the user entered URL into a QR code image.
3. Create a txt file to save the user input using the native fs node module.
*/

import inquirer from "inquirer";
import qr from "qr-image";
import fs from "fs";

inquirer
  .prompt([
    {
        name: 'URL',
        message: 'Type The URL: '
    },
  ])

  .then((answers) => 
  {
    console.info('Status: ',"Recieved Sucessfully!");
    const url= answers.URL;
    var qr_png = qr.image(url);
    qr_png.pipe(fs.createWriteStream('qr-image.png'));
 
    fs.writeFile('URL1.txt',url,(err) => {
        if (err) throw err;
        console.log("The File Has Been Saved!")
    })
    
    fs.readFile('./URL1.txt','utf-8',(err,data) => {
        if (err) throw err;
        console.log(`The URL is ${data}`);
    })
  })