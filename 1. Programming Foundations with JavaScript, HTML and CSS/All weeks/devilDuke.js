// Write the code to change the Duke blue devil to be yellow. (See Duke Blue Devil below.)

var img = new SimpleImage("duke_blue_devil.png");

for (var pixel of img.values()) {
    if (pixel.getRed() == 0) {
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
}

print(img);