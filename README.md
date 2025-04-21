[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE.md)

# Miscellaneous Application Utilities for Java

This project collects classes extracted from earlier Java (Swing) GUI applications I wrote to (hopefully) enable more
rapid and less cumbersome publication of future applications as the same basic components and processes are required
again and again. In particular, this includes a universal license/about dialog implementation, together with disclaimer
handling, and a buffer to quickly get logs displayed on GUI when using Log4J.

Development focus is on use for my own applications, but you are welcome to start using this project in your applications
as well.

## Applications using this library

A possibly incomplete list of (published) applications currently depending on this library:

- [Legacy status proxy for VATSIM](https://github.com/dneuge/legacy-status-proxy-vatsim) (where this library was originally extracted from)

## Compatibility

As long as applications using this library support Java 8 in current development state, this library has to remain compatible to Java 8 as well.

Runtime dependencies should be kept to a minimum. Log4J is a notable exception as it is generally used in all applications this library is originally
intended to be used for.

## License

All source code and original resources within this project are released under [MIT license](LICENSE.md), unless declared otherwise
(e.g. in file contents). Please be aware that dependencies (e.g. libraries and/or external data used by this
project) are subject to their own respective licenses which can affect distribution, particularly in binary/packaged
form.

[`src/main/resources/de/energiequant/apputils/misc/attribution/`](src/main/resources/de/energiequant/apputils/misc/attribution/) contains commonly used license
texts which are (self-)copyrighted by their respective owners and have been kept unaltered, apart from adding necessary markup/notes to be properly displayed
using the components of this library. Inclusion, distribution and display/reproduction of those license texts is done in order to ease achieving license
conformity for components distributed under those licenses that are possibly being included to applications using this library. No additional copyright is
claimed over those license texts. Those files, as included within this library, shall not be used for any purpose other than license conformity.

### Note on the use of/for AI

Usage for AI training is subject to individual source licenses, there is no exception. This generally means that proper
attribution must be given and disclaimers may need to be retained when reproducing relevant portions of training data.
When incorporating source code, AI models generally become derived projects. As such, they remain subject to the
requirements set out by individual licenses associated with the input used during training. When in doubt, all files
shall be regarded as proprietary until clarified.

Unless you can comply with the licenses of this project you obviously are not permitted to use it for your AI training
set. Although it may not be required by those licenses, you are additionally asked to make your AI model publicly
available under an open license and for free, to play fair and contribute back to the open community you take from.

AI tools are not permitted to be used for contributions to this project. The main reason is that, as of time of writing,
no tool/model offers traceability nor can today's AI models understand and reason about what they are actually doing.
Apart from potential copyright/license violations the quality of AI output is doubtful and generally requires more
effort to be reviewed and cleaned/fixed than actually contributing original work. Contributors will be asked to confirm
and permanently record compliance with these guidelines.
