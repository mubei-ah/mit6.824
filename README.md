# mit6.824
6.824lab


## mapReduce
统计词频率为例
分成mapFunction 和 reduceFunction

mapFunction：将分词结果记成 （“word”，“1”）... 这样的结构
reduceFunction：将mapFunction 生成的结果统计,没一个单词的数量