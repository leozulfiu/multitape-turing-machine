# dynamic trees with box2d

Inspired by [Badland](https://play.google.com/store/apps/details?id=com.frogmind.badland&hl=de) I created a small
simulation where I test realistic tree behaviour. RevoluteJoins are used to connect the tree parts.

![Alt text](/screenshots/screenshot.png?raw=true "screenshot")

## Installation

You need [gradle](http://gradle.org/) to run the application.

## Usage

1. `git clone https://github.com/leozulfiu/dynamic-trees-box2d.git`
2. `cd dynamic-trees-box2d`
3. `gradle run`

For creating a jar file just type `gradle build`. You will find the jar in the build/libs/ folder.
The Start.tmx file is created with [Tiled](http://www.mapeditor.org/). You can change the level to your own desires.

## License

MIT
