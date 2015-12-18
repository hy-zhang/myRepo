# Work plan

## Week 1 (~ Dec 20, 2015)

- Nested interfaces: generate of() methods for a program with many families
- Independent extensibility: generate code for combining features automatically
- Read paper: [On Inner Classes](http://www.cis.upenn.edu/~bcpierce/fji.ps) by Igarashi

## Week 2 (~ Dec 27, 2015)

- Encoding of the EPL case study (with new annotation applied)
- Specification of annotation processing
- Experiments
	- Experiments on how Lombok and conventional annotation processor interact
	- Experiments on Lombok with multiple files
	- Experiments on generics in Lombok


## Week 3 (~ Jan 3, 2016)

- Keep doing experiments from last week

## Week 4 (~ Jan 10, 2016)

- Develop new features
- Think about some more interesting case studies (like [SPL](http://www.splot-research.org/))

## Week 5 (~ Jan 17, 2016)

- Coding and formalization

## Week 6 (~ Jan 24, 2016)

- Set aside to have more discussion, coding and debugging
- Paper sketch

## Week 7 (~ Jan 31, 2016)

- Case studies
- Writing

## Week 8 (~ Feb 7, 2016)

- Writing

## Week 9 (~ Feb 14, 2016)

Chinese New Year

## Week 10 (~ Feb 21, 2016)

Chinese New Year

## Week 11 (~ Feb 28, 2016)

- Writing

## Week 12 (~ Mar 6, 2016)

- Writing and polishing

## Week 13 (~ Mar 13, 2016)

- Writing and polishing

## Week 14 (~ Mar 20, 2016)

- Writing and polishing

### > Notes

```
interface A {
    int x();
    interface B { // Static nested interface.
        default int m() { return x(); } // rejected
        // Can declare x() as a static method
        // Or declare a "field" in B, like: A thisA();
        // Then use thisA.x() instead
    }
}
```