# Variables
java_class=Content_Reading
directory_path="./"
stop_words=("the" "and" "is" "in" "it" "of" "to" "a" "this" "for" "on" "at" "an" "by" "as" "that" "which" "not" "are" "you" "we" "can" "be" "from" "or" "have" "has" "was" "your" "his" "her" "its" "they")

# To Compile The Java program
javac "$java_class.java"

# TO Run The Java program
java "$java_class" "$directory_path" "${stopwords[@]}"

rm $java_class.class
