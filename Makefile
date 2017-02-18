.PHONY: autobuild nodemon

autobuild:
	lein cljsbuild auto dev

nodemon:
	nodemon out/server.js

clean:
	rm -rf out
