<p align="center">
 <img src="art/logo-anim.gif" width="40%">
</p>
<br>

# Airline Ticketor
## Project Tag & ID
<b>Description: </b>It is a native android application that allows airline tickets to be listed. 
<br>
<b>Platform: </b> Android <br>
<b>Language: </b> Kotlin <br>
<b>Architecture: </b> <code>Model <> View <> ViewModel </code> <br>

## Frameworks
<ul>

<li>
<b><code>retrofit</code>: </b> Retrofit turns your HTTP API into a Java interface.
<br>
</li>

<li>
<b><code>mockfit</code>: </b> It is a lightweight package that makes mock data easy to use. <br>
</li>

<li>
<b><code>glide</code>: </b> Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface. <br>
</li>

<li>
<b><code>hilt</code>: </b> Hilt provides a standard way to incorporate Dagger dependency injection into an Android application. <br>
</li>


</ul>


## Our Components & Logics


<b><code>FlightTabItem</code>: </b>It is a simple component that makes it easy to create and manage the children of the android tablayout component. <br>

```kotlin
class FlightTabItem private constructor(
    private val context: Context,
    private val parent: ViewGroup,
    private val tabLayout: TabLayout,
    val data: FlightTabData,
    private val listener: OnFlightTabClickListener? 
)
```
just Create. that is all!
```kotlin
 FlightTabItem.create(context,parent,tabLayout,tabData){
      ...
  }
```
## Unit Tests

<li>
<code>Unicode Parser</code>: A method that captures unicode characters in a text or string and returns their char equivalent. test has been written.
</li>
<br>

## Screen Recording
<br>
<img src="art/app.gif" width="40%">

