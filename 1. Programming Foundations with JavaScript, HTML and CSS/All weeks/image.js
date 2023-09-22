var img = new SimpleImage(200,200);
for (var px of img.values()){
  var x = px.getX();
  var y = px.getY();
  if (x < img.getWidth()/2){
    px.setRed(255);
  }
  if (y>img.getHeight()/2){
    px.setBlue(255);
  }
  if (x > img.getWidth()/2 && y < img.getHeight()/2){
    px.setGreen(255);
  }
}
print (img);

// yellow in rgb is 255,255,0
// green in rgb is 0,255,0
// blue in rgb is 0,0,255
// red in rgb is 255,0,0
// magenta in rgb is 255,0,255
// black in rgb is 0,0,0