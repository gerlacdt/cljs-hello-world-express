.PHONY: autobuild nodemon

watch:
	lein cljsbuild auto dev

watch-node:
	nodemon out/server.js

clean:
	rm -rf out
