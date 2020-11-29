HTMLやJSファイルをjavaでべた書きするために前後にprintlnメソッドを付けるツール  
出力ファイル名は末尾にPrintlnと名称がつく  
ex. test.js -> testPrintln.js  

# USAGE
java CombinePrintln [任意のファイルパス]

# NOTE
文字化けした際は、BufferedWriterなどに文字コード指定してください。
