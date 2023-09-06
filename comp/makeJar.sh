./build.sh
if [ -n "$(ls -A ./../lib)" ]; then
  if [ ! -f ./../jarFiles/lib ]; then
    mkdir ./../jarFiles/lib
  fi
  cp ./../lib/* ./../jarFiles/lib
else
  if [ -e ./../jarFiles/lib ]; then
    rm -r ./../jarFiles/lib
  fi
fi

cd ./../forJar || echo "ERROR: Command 'cd'"
java GenerateManifest.java
cd ./../classes/ || echo "ERROR: Command 'cd'"
jar -cvmf ./../forJar/MANIFEST.MF ./../jarFiles/Simdev.jar ./Simdev/*