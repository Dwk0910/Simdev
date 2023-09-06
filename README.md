![build_passing](https://img.shields.io/badge/build-passing-green)
![lang_badge](https://img.shields.io/badge/build_language-Java-yellow)
![ver_badge](https://img.shields.io/badge/version-1.0.0-blue)

# Simdev
*더 간편하고 더 쉽게*

Simdev는 `Simple Devtool`이란 뜻을 가지며, `Java Project Generator`입니다. <br/>
Dwk0910이 Github에 올리는 거의 모든 프로젝트는 Simdev를 통해 개발됩니다.

Simdev는 이전의 다른 devtool과는 **훨씬 간단하고 쉽게** 사용이 가능하며 <br/>
누구나 사용가능하고, 배포도 쉽게 할 수 있도록 설계했습니다.

## How to use
Simdev JAR파일은 https://github.com/Dwk0910/Simdev/releases/tag/Simdev <- 이곳에서 다운받으실 수 있습니다.

### Runnable JAR File
Simdev는 `.jar` 파일 형식으로 배포됩니다.
사용자는 아래의 명령만 입력하면, Simdev의 친절한 GUI 시스템을 볼 수 있습니다.

```
java -jar Simdev.jar
```

### Simdev GUI
<img width="801" alt="image" src="https://github.com/Dwk0910/Simdev/assets/66874914/1e0907d0-6d53-4b25-bcb9-b1f323f7facd">

### Easy Use
Simdev는 GUI에 써진대로만 하면 사용하는 데에 어려움이 전혀 없습니다.
아래의 표를 보고 각 항목마다 어떻게 값을 넣어야 할지 생각해보세요.
| 이름 | 설명 |
| --- | --- |
| 프로젝트 이름 | 프로젝트 이름을 넣으세요. 이 이름은 `Main Class File`의 상위 폴더의 이름이 됩니다. |
| 프로젝트 경로 | 프로젝트를 만들 경로를 넣으세요. `경로 선택` 버튼을 누르시면 친절한 GUI에서 폴더를 직접 선택하실 수 있습니다.<br/>**이때 폴더는 비어있어야 합니다** |
| Main Class 이름 | `Main Class File` 파일의 이름을 지정합니다. 예를 들어 `Example`을 입력하였다면, 파일의 이름은 `Example.java`가 됩니다 |
| 개발 환경 | 자신이 개발할 환경을 설정합니다. 이는 run파일, build파일 등의 확장자와 연관이 있습니다.<br/>자신의 운영체제를 확인하시고 선택하시면 됩니다 |

좋습니다! 이제 프로젝트가 생성될 일만 남았습니다.<br/>
오른쪽 아래의 `프로젝트 생성` 버튼을 누르면 Simdev 프로젝트가 얼마 걸리지 않아 생성됩니다.

## File Tree
```
├── ProjectName
│   └── MainClass.java
├── classes
├── comp
│   ├── build.bat
│   ├── build.sh
│   ├── makeJar.bat
│   ├── makeJar.sh
│   ├── run.bat
│   └── run.sh
├── forJar
│   ├── GenerateManifest.java
│   └── MANIFEST.MF
├── jarFiles
└── lib
```

## How to run, build, publish
### RUN
Simdev에서는 언제든지 테스트를 쉽게 할 수 있습니다. 그저 `run`파일을 실행하기만 하면 된답니다.<br/>
자신이 어떤 library를 쓴다고 classpath를 추가할 필요도 없습니다.<br/>
그런 귀찮은 작업들은 모두 Simdev가 알아서 해준답니다.

### BUILD
Simdev에서의 build는 매 파일들마다 알아서 실행됩니다.<br/>
build도 run과 마찬가지로 파일 한번만 실행하면 알아서 귀찮은 작업들을 대신 해주고, 빌드하도록 설계했습니다.

### PUBLISH
Simdev에서는 쉬운 배포를 위해 `Runnable JAR File` 만들기 기능을 지원합니다<br/>
`makeJar`파일을 실행하게 되면 Simdev는 `MANIFEST.MF`, classpath 등 귀찮은 작업들을 대신해주고,<br/>
`Runnable JAR File`을 `jarFiles`폴더 아래에 만들게 됩니다.<br/>
이떄 만들어진 JAR 파일은 `Runnable JAR File` 이므로 아래의 명렁어만 넣으면 실행 할 수 있답니다.

```
java -jar {FileName}.jar
```

## How to use Library
Simdev에서는 lib폴더에 직접 JAR파일을 넣는 방식을 채택하고 있습니다.<br/>
따라서 library를 사용할 예정이라면 JAR파일을 받아서, lib폴더 아래에 풀기만 하면 실행, 빌드, 배포할때에 언제든지 사용이 가능합니다.<br/>
(IDE에서는 따로 설정을 해줘야 합니다)

# Final
자바의 다른 Devtool에 익숙하지 않고 어려운 분들이 쉽게 접할 수 있도록 최선을 다해서 설계, 제작하였습니다.<br/>
버그나 불편사항 등 제보는 언제나 환영이랍니다: <br/>
`Discord / Dwk0910#4491` (1인개발이므로 반영이 상당히 느릴 수 있습니다. 양해바랍니다.)

***Thanks for reading!***<br/>
*Copyright 2023. Dwk0910 All rights reserved.*
