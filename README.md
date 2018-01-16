## Deserialization

Just do `sbt run` and then go to `localhost:9000/redis/asdf`

The expiration time for the key is 5 seconds. If you do two request in less than this time, the first time the request 
will work fine but in the next request you will have the following issue:

``` 
[error] p.a.cache.redis - Deserialization failed for key 'troll'.
java.io.NotSerializableException: No configured serialization-bindings for class [java.lang.Object]
        at akka.serialization.Serialization.serializerFor(Serialization.scala:238)
        at akka.serialization.Serialization.$anonfun$deserialize$3(Serialization.scala:204)
        at scala.util.Try$.apply(Try.scala:209)
        at akka.serialization.Serialization.deserialize(Serialization.scala:204)
        at play.api.cache.redis.connector.AkkaDecoder.binaryToAnyRef(AkkaSerializer.scala:122)
        at play.api.cache.redis.connector.AkkaDecoder.$anonfun$stringToAnyRef$2(AkkaSerializer.scala:126)
        at scala.Function1.$anonfun$andThen$1(Function1.scala:52)
        at play.api.cache.redis.connector.AkkaDecoder.stringToAnyRef(AkkaSerializer.scala:126)
        at play.api.cache.redis.connector.AkkaDecoder.untypedDecode(AkkaSerializer.scala:113)
        at play.api.cache.redis.connector.AkkaDecoder.decode(AkkaSerializer.scala:97)

```

## Context

Again, do `sbt run` and this time go the following endpoint: `localhost:9000/context/asdf`.

You will see that the context is not available anymore.