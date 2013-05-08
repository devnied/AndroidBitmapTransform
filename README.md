AndroidBitmapTransform
======================

AndroidBitmapTransform is an useful library to do Bitmap transformation on Android.<br/>
<b>Current version : 1.0.0</b> 

##Simple API

It is very easy to get started with AndroidBitmapTransform:

* Exemple to Multiply two bitmaps

```java
ImageView view = (ImageView) findViewById(R.id.bitmapResult);
Bitmap bitmap = BitmapTransform.createBitmap(this,R.drawable.dst, R.drawable.src, PorterDuff.Mode.MULTIPLY, true, false);
view.setImageBitmap(bitmap);
```

* Exemple to Add two bitmaps

```java
ImageView view = (ImageView) findViewById(R.id.bitmapResult);
Bitmap bitmap = BitmapTransform.createBitmap(this,R.drawable.dst, R.drawable.src, PorterDuff.Mode.ADD, true, false);
view.setImageBitmap(bitmap);
```

* List of PorterDuff modes

![](https://raw.github.com/devnied/AndroidBitmapTransform/master/%20Xfermodes.png)

## Maven

```xml
<dependency>
  <groupId>com.github.devnied.AndroidBitmapTransform</groupId>
  <artifactId>library</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Bugs

Please report bugs and feature requests to the GitHub issue tracker.<br/>
Forks and Pull Requests are also welcome.

## Author

**Millau Julien**

+ [http://twitter.com/devnied](http://twitter.com/devnied)
+ [http://github.com/devnied](http://github.com/devnied)


## Copyright and license

Copyright 2013 Millau Julien.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
